package com.github.milomarten.bittwiddle.factory.gba;

import com.github.milomarten.bittwiddle.factory.NoSuchFileException;
import com.github.milomarten.bittwiddle.factory.OutOfFileException;
import com.github.milomarten.bittwiddle.model.*;
import com.github.milomarten.bittwiddle.operation.DynamicByteParser;
import com.github.milomarten.bittwiddle.operation.ReadOp;
import com.github.milomarten.bittwiddle.operation.StaticByteParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GBARead implements ReadOp {
    public GBAFile handler;
    public Pipe steps;

    GBARead(GBAFile file) {
        this.handler = file;
        this.steps = Empty.INSTANCE;
    }

    GBARead(GBAFile file, long offset) {
        this.handler = file;
        this.steps = raf -> raf.seek(offset);
    }

    GBARead(GBAFile file, Pipe steps) {
        this.handler = file;
        this.steps = steps;
    }

    protected <T> T doTerminalAction(CheckedFunction<RandomAccessFile, T> retriever) {
        try {
            RandomAccessFile raf = new RandomAccessFile(handler.getFile(), "r");
            steps.apply(raf);
            T object = retriever.apply(raf);
            raf.close();
            return object;
        } catch (Exception e) {
            throw new OutOfFileException(e);
        }
    }

    @Override
    public ReadOp advance(int n) {
        Pipe nextStep = steps.then(file -> file.skipBytes(n));
        return new GBARead(this.handler, nextStep);
    }

    @Override
    public ReadOp follow() {
        Pipe nextStep = steps.then(file -> {
            byte[] bites = new byte[GBAPointer.PARSER.numberOfBytes()];
            file.read(bites);
            GBAPointer p = GBAPointer.PARSER.read(bites);
            if(p.getBank() == GBAPointer.Bank.ROM) {
                file.seek(p.getOffset());
            }
            throw new InvalidPointerException("Pointer " + p + " does not point to ROM memory");
        });
        return new GBARead(this.handler, nextStep);
    }

    @Override
    public Bit bit(int index) {
        return null;
    }

    @Override
    public SignedByte signedByte() {
        return doTerminalAction(raf -> SignedByte.from(raf.readByte()));
    }

    @Override
    public UnsignedByte unsignedByte() {
        return doTerminalAction(raf -> UnsignedByte.from(raf.readUnsignedByte()));
    }

    @Override
    public SignedShort signedShort() {
        return doTerminalAction(raf -> SignedShort.from(raf.readShort()));
    }

    @Override
    public UnsignedShort unsignedShort() {
        return doTerminalAction(raf -> UnsignedShort.from(raf.readUnsignedShort()));
    }

    @Override
    public SignedWord signedWord() {
        return doTerminalAction(raf -> SignedWord.from(raf.readInt()));
    }

    @Override
    public UnsignedWord unsignedWord() {
        return doTerminalAction(raf -> UnsignedWord.from(Integer.toUnsignedLong(raf.readInt())));
    }

    @Override
    public <T> T get(StaticByteParser<T> parser) {
        return doTerminalAction(raf -> {
            byte[] bites = new byte[parser.numberOfBytes()];
            int byteCount = raf.read(bites);
            if(parser.numberOfBytes() != byteCount) {
                throw new OutOfFileException("Expected " + parser.numberOfBytes() + " bytes, got " + byteCount);
            }
            return parser.read(bites);
        });
    }

    @Override
    public <T> T get(DynamicByteParser<T> parser) {
        try {
            Naive naive = new Naive(this.handler, this.steps);
            T object = parser.read(naive);
            naive.close();
            return object;
        } catch (IOException e) {
            throw new NoSuchFileException(e);
        }
    }

    private interface Pipe {
        void apply(RandomAccessFile file) throws Exception;

        default Pipe then(Pipe after) {
            Pipe me = this;
            return file -> {
                me.apply(file);
                after.apply(file);
            };
        }
    }

    private enum Empty implements Pipe {
        INSTANCE;

        @Override
        public void apply(RandomAccessFile file) throws Exception {
            // Do nothing
        }

        @Override
        public Pipe then(Pipe after) {
            return after;
        }
    }

    private interface CheckedFunction<T, R> {
        R apply(T input) throws Exception;
    }

    private static class Naive extends GBARead {
        private final RandomAccessFile raf;

        Naive(GBAFile file, Pipe steps) throws FileNotFoundException {
            super(null, steps);
            raf = new RandomAccessFile(file.getFile(), "r");
        }

        @Override
        protected <T> T doTerminalAction(CheckedFunction<RandomAccessFile, T> retriever) {
            try {
                steps.apply(raf);
                return retriever.apply(raf);
            } catch (Exception e) {
                throw new OutOfFileException(e);
            }
        }

        public void close() throws IOException {
            raf.close();
        }
    }
}

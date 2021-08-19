package com.github.milomarten.bittwiddle.factory.gba;

import com.github.milomarten.bittwiddle.operation.ReadOp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GBAFile {
    @Getter(AccessLevel.PACKAGE)
    private final File file;

    public static GBAFile of(File file) {
        return new GBAFile(file);
    }

    public ReadOp read() {
        return new GBARead(this);
    }

    public ReadOp read(long offset) {
        return new GBARead(this, offset);
    }
}

package com.github.milomarten.bittwiddle.factory.gba;

import com.github.milomarten.bittwiddle.operation.StaticByteParser;
import lombok.Data;

@Data
public class GBAPointer {
    public static final StaticByteParser<GBAPointer> PARSER = new StaticByteParser<>() {
        @Override
        public int numberOfBytes() {
            return 4;
        }

        @Override
        public GBAPointer read(byte[] bites) {
            Bank bank = Bank.byByte(bites[0]);
            int offset = (bites[1] << 16) | (bites[2] << 8) | bites[3];
            return new GBAPointer(bank, offset);
        }

        @Override
        public byte[] write(GBAPointer object) {
            return new byte[] {
                    object.bank.bite,
                    (byte)(object.offset >>> 16),
                    (byte)(object.offset >>> 8),
                    (byte)(object.offset)
            };
        }
    };

    private final Bank bank;
    private final int offset;

    /**
     * The Memory Bank this pointer points to
     */
    public enum Bank {
        BIOS(0),
        ON_BOARD_WRAM(2),
        ON_CHIP_WRAM(3),
        IO(4),
        PALETTE(5),
        VRAM(6),
        OAM(7),
        ROM(8),
        SRAM(14);

        byte bite;

        Bank(int bite) {
            this.bite = (byte)bite;
        }

        public static Bank byByte(byte bite) {
            for(Bank b : Bank.values()) {
                if(b.bite == bite) {
                    return b;
                }
            }
            throw new InvalidPointerException("Unknown bank " + bite);
        }
    }
}

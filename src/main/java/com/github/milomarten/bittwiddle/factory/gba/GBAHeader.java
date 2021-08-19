package com.github.milomarten.bittwiddle.factory.gba;

import com.github.milomarten.bittwiddle.model.UnsignedByte;
import com.github.milomarten.bittwiddle.model.UnsignedWord;
import lombok.Data;

@Data
public class GBAHeader {
    private UnsignedWord entryPoint;
    private UnsignedByte[] logo;
    private String title;
    private String gameCode;
    private String makerCode;
    private UnsignedByte fixed;
    private UnsignedByte mainUnitCode;
    private UnsignedByte deviceType;
    private UnsignedByte softwareVersion;
    private UnsignedByte complementCheck;
}

package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;

public class MarketResponseDto {

    private BigInteger marketId;

    private String marketName;

    private BigInteger divisionId;

    private String iso2CountryCode;

    public MarketResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MarketResponseDto(BigInteger marketId, String marketName, BigInteger divisionId, String iso2CountryCode) {
        this.marketId = marketId;
        this.marketName = marketName;
        this.divisionId = divisionId;
        this.iso2CountryCode = iso2CountryCode;
    }

    public BigInteger getMarketId() {
        return marketId;
    }

    public void setMarketId(BigInteger marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public BigInteger getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(BigInteger divisionId) {
        this.divisionId = divisionId;
    }

    public String getIso2CountryCode() {
        return iso2CountryCode;
    }

    public void setIso2CountryCode(String iso2CountryCode) {
        this.iso2CountryCode = iso2CountryCode;
    }
}
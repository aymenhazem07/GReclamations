package com.example.demo.xmlExport;

import com.example.demo.model.Claim;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "claims")
public class ClaimListWrapper {
    private List<Claim> claims;

    public ClaimListWrapper() {
        this.claims = new ArrayList<>();
    }

    public ClaimListWrapper(List<Claim> claims) {
        this.claims = claims;
    }

    @XmlElement(name = "claim")
    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
}

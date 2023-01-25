package com.earthdaily.pos.api.contract.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pos {

    @Id
    private String id;

    private double lat;
    private double lng;
    private String name;
}

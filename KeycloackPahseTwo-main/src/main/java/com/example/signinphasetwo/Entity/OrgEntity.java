package com.example.signinPhasetwo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class OrgEntity {


    private String name;


    private String id;

    private String realm;
    private String displayName;
    private String url;
    private String domain;
    private String attributes;





}

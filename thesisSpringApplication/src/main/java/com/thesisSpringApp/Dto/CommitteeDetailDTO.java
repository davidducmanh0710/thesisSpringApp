package com.thesisSpringApp.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CommitteeDetailDTO {
    private String name;
    private List<CommitteeUserDetailDTO> members;
}

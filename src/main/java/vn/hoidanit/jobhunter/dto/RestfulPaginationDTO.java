package vn.hoidanit.jobhunter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestfulPaginationDTO {
    private Meta meta;
    private Object result;
}

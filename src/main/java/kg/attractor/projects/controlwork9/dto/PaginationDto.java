package kg.attractor.projects.controlwork9.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDto<T> {
    List<T> items;
    int page;
    int totalPages;
    boolean hasPreviousPage;
    boolean hasNextPage;
}

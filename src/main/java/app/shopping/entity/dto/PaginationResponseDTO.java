package app.shopping.entity.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class PaginationResponseDTO {
    private int currentPage;
    private List<?> data;
    private int lastPage;
    private int perPage;
    private long total;
}

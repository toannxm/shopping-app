package app.shopping.entity.mapper;

import app.shopping.entity.dto.PaginationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationResponseMapper {

    public PaginationResponseDTO toDTO(Page<?> page) {
        PaginationResponseDTO paginationResponseDTO = new PaginationResponseDTO();
        paginationResponseDTO.setCurrentPage(page.getPageable().getPageNumber());
        paginationResponseDTO.setData(page.getContent());
        paginationResponseDTO.setPerPage(page.getPageable().getPageSize());
        paginationResponseDTO.setTotal(page.getTotalElements());
        paginationResponseDTO.setLastPage(page.getTotalPages() - 1);
        return paginationResponseDTO;
    }
}

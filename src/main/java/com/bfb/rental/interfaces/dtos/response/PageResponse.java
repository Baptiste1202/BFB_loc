package com.bfb.rental.interfaces.dtos.response;
import lombok.*;

import java.util.List;

/**
 * JUSTIFICATION : DTO générique pour paginer les réponses
 *
 * Au lieu de mettre la pagination dans chaque DTO,
 * on crée un wrapper générique.
 *
 * PATTERN : Wrapper Pattern / Generic Response Pattern
 * BENEFIT : Réutilisable pour TOUS les endpoints paginés
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
    private boolean isFirst;

    /**
     * Factory method pour créer depuis un Page Spring
     */
    public static <T> PageResponse<T> from(org.springframework.data.domain.Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}

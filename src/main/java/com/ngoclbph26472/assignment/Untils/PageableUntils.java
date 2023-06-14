package com.ngoclbph26472.assignment.Untils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableUntils {
    public static Pageable pageableUtils(int pageIndex, int pageSize) {
//        Direction direction = null;
//        direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
//        Sort sort = Sort.by(direction,sortBy);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return pageable;
    }
}

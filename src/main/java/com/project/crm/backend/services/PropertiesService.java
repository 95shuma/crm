package com.project.crm.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PropertiesService {
    private final SpringDataWebProperties pageableDefaultProps;

    public int getDefaultPageSize() {
        return pageableDefaultProps.getPageable().getDefaultPageSize();
    }

    public void setDefaultPageSize(int pageSize){
        pageableDefaultProps.getPageable().setDefaultPageSize(pageSize);
    }

    public static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("pageNumber", list.getNumber());
        model.addAttribute("pageSize", list.getSize());
        model.addAttribute("url", uri);

    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

}

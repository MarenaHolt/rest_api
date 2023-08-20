package com.demoqa.models;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksListModel {

    private String userId;
    private List<IsbnModel> collectionOfIsbns;

}

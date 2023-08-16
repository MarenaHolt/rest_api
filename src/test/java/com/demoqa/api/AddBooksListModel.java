package com.demoqa.api;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksListModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}

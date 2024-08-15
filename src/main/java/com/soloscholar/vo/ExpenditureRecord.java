package com.soloscholar.vo;

import java.util.Date;

public record ExpenditureRecord(Long id, String invoicenumber, String type, String category, double rate, int quantity, Date date, double totalPrice) {

}

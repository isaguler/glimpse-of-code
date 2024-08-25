package com.isaguler.html_converter.service;

public interface Converter<Req, Res> {

    Res convert(Req request);
}

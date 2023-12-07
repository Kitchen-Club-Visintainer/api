package br.com.kitchen.club.dto.auth;

import java.util.ArrayList;
import java.util.List;

public class Response<TokenDto> {

    private TokenDto data;
    private List<String> errors;

    public Response() {
    }

    public TokenDto getData() {
        return data;
    }

    public void setData(TokenDto data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}

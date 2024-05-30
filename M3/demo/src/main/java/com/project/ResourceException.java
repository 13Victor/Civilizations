package com.project;

public class ResourceException extends Exception {
    public ResourceException(String message, int ironCostSmithy, int iron) {
        super(message);
    }

    public ResourceException(String message){
        super(message);
    }
}

   

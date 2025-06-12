package org.sysestoque.utils;

import java.nio.file.Paths;

public class PathFxml {
    // o pacote util retorna o path completo no parent root
    //metodo static possibilita usar a classe sem ter que instânciar o objeto
    public static String pathBase(){
        return Paths.get("src/main/java/org/sysestoque/view").toAbsolutePath().toString();
    }
}

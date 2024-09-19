package br.com.AluraMusic.demo.model;

public enum EnumTipo {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipo;

    EnumTipo(String tipo) {
        this.tipo = tipo;
    }

    public static EnumTipo fromString(String tipo) {
        for(EnumTipo tipoEnum : EnumTipo.values()) {
            if(tipoEnum.tipo.equalsIgnoreCase(tipo)) {
                return tipoEnum;
            } 
        }
        throw new IllegalArgumentException("Nenhuma tipo encontrada para a string fornecida: " + tipo);
    }
}

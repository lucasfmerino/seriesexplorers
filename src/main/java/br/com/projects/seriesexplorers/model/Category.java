package br.com.projects.seriesexplorers.model;

public enum Category {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    TERROR("Terror"),
    ANIMACAO("Animation");

    private String omdbCategory;

    Category(String omdbCategory){
        this.omdbCategory = omdbCategory;
    }

    public static Category fromPortuguese(String text) {
        for (Category category : Category.values()) {
            if (category.omdbCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}

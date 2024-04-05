package br.com.projects.seriesexplorers.model;

public enum Category {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    TERROR("Terror", "Terror"),
    ANIMACAO("Animation", "Animação");

    private String omdbCategory;
    private String portugueseCategory;

    Category(String omdbCategory, String portugueseCategory){
        this.omdbCategory = omdbCategory;
        this.portugueseCategory = portugueseCategory;
    }

    public static Category fromOmdb(String text) {
        for (Category category : Category.values()) {
            if (category.omdbCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Category fromPortuguese(String text) {
        for (Category category : Category.values()) {
            if (category.portugueseCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}

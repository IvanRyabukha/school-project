package org.riv.webschool.entity;

public enum Subject {
    MATH(13), HISTORY(14), LITERATURE(15), BIOLOGY(16), CHEMISTRY(17);

    private final int id;

    Subject(int id) {
        this.id = id;
    }

    public static Subject getById(int id) {
        for (Subject subject : Subject.values()) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        throw new IllegalArgumentException("No enum constant with id " + id);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

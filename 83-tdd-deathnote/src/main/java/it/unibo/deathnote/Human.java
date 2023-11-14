package it.unibo.deathnote;

import java.util.Objects;

public class Human {
    final private String name;
    private String cause = "";
    private String details = "";

    public Human(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(final String cause) {
        this.cause = cause;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    /**@implNote
     * Implemented equals using this website https://www.technofundo.com/tech/java/equalhash.html
     * */
    @Override
    public boolean equals(Object o) {
        /*If the object is compared with itself return true*/
        if (o == this) {
            return true;
        }
        /*If object isn't an instanceof Human return false*/
        if (!(o instanceof Human)) {
            return false;
        }
        Human h = (Human) o;
        return this.name.equals(h.name);
    }

    /**@implNote
     * Implemented hashCode using this website https://www.technofundo.com/tech/java/equalhash.html
     * */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (null == name ? 0 : name.hashCode());
        return hash;
    }
}

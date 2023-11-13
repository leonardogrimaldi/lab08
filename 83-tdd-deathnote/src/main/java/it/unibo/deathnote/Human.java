package it.unibo.deathnote;
public class Human {
        final private String name;
        private String cause;
        private String details;

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
}

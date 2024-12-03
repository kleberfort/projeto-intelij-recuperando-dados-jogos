public class Team {

    private String name;
    private String image;

    private String classification;

    public String getName() {
        return name;
    }

    public Team() {
    }

    public Team(String name, String image, String classification) {
        this.name = name;
        this.image = image;
        this.classification = classification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }




    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}

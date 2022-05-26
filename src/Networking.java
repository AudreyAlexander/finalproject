import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Networking {
    private String baseURL;


    public Networking() {baseURL = "https://swapi.dev/api/";}

    public String makeAPICall(String name, String category) {
        String URL = "";
        if (category.equals("films")){
            URL =  baseURL + category + "/" + name + "/";
        }
        else{
            URL = baseURL + category + "/?search=" + name;
        }

        System.out.println(URL);
        try {
            URI myUri = URI.create(URL);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request,    HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String parsePlanetData(String json){
        JSONObject jsonObj = new JSONObject(json);
        JSONArray swObj = jsonObj.getJSONArray("results");
        JSONObject newObj = swObj.getJSONObject(0);
        String name = newObj.getString("name");
        String rotation = newObj.getString("rotation_period");
        String orbital = newObj.getString("orbital_period");
        String diameter = newObj.getString("diameter");
        String climate = newObj.getString("climate");
        String gravity = newObj.getString("gravity");
        String terrain = newObj.getString("terrain");
        String population = newObj.getString("population");

        String info = "Name: " + name + "\nRotation Period: " + rotation + " days\nOrbital Period: " + orbital + " days\nDiameter: " + diameter + " m\nClimate: " + climate + "\nGravity: " + gravity + "\nTerrain: " + terrain + "\nPopulation: " + population;
        return info;
    }

    public String parseSVData(String json){
        JSONObject jsonObj = new JSONObject(json);
        JSONArray swObj = jsonObj.getJSONArray("results");
        JSONObject newObj = swObj.getJSONObject(0);
        String name = newObj.getString("name");
        String model = newObj.getString("model");
        String manufacturer = newObj.getString("manufacturer");
        String cost = newObj.getString("cost_in_credits");
        String speed = newObj.getString("max_atmosphering_speed");
        String passengers = newObj.getString("passengers");

        String info = "Name: " + name + "\nModel: " + model + "\nManufacturer: " + manufacturer + " \nCost: " + cost + " credits\nMax Atmosphering Speed: " + speed + "\nPassengers: " + passengers;
        return info;
    }

    public String parsePeopleData(String json){
        JSONObject jsonObj = new JSONObject(json);
        JSONArray swObj = jsonObj.getJSONArray("results");
        JSONObject newObj = swObj.getJSONObject(0);
        String name = newObj.getString("name");
        String height = newObj.getString("height");
        String mass = newObj.getString("mass");
        String hair = newObj.getString("hair_color");
        String skin = newObj.getString("skin_color");
        String eye = newObj.getString("eye_color");
        String birthYear = newObj.getString("birth_year");

        String info = "Name: " + name + "\nHeight: " + height + " cm\nMass: " + mass + "kg\nHair Color: " + hair + "\nSkin Color: " + skin + "\nEye Color: " + eye + "\nBirth Year: " + birthYear;
        return info;
    }

    public String parseFilmData(String json){
        JSONObject jsonObj = new JSONObject(json);
        String name = jsonObj.getString("title");
        int ep = jsonObj.getInt("episode_id");
        String dir = jsonObj.getString("director");
        String prod = jsonObj.getString("producer");
        String date = jsonObj.getString("release_date");

        String info = "Title: " + name + "\nEpisode: " + ep + "\nDirector: " + dir + "\nProducer(s): " + prod + "\nRelease Date: " + date;
        return info;
    }

    public String parseSpeciesData(String json){
        JSONObject jsonObj = new JSONObject(json);
        JSONArray swObj = jsonObj.getJSONArray("results");
        JSONObject newObj = swObj.getJSONObject(0);
        String name = newObj.getString("name");
        String classif = newObj.getString("classification");
        String des = newObj.getString("designation");
        String height = newObj.getString("average_height");
        String life = newObj.getString("average_lifespan");
        String lang = newObj.getString("language");

        String info = "Name: " + name + "\nClassification: " + classif + "\nDesignation: " + des + "\nAvg Height: " + height + " cm\nAvg Lifespan: " + life + "\nLanguage: " + lang;
        return info;
    }
}
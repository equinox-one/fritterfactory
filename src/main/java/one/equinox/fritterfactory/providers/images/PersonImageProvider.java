package one.equinox.fritterfactory.providers.images;

import one.equinox.fritterfactory.providers.ListItemProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateuyabar on 18/12/15.
 */
public class PersonImageProvider extends ListItemProvider<String> {
    private static List<String> images;

    private static List<String> getImages(){
        if(images == null){
            images = new ArrayList<String>();
            for(int i=0; i<50; ++i){
                String manUrl = "http://api.randomuser.me/portraits/men/"+i+".jpg";
                String womenUrl = "http://api.randomuser.me/portraits/women/"+i+".jpg";
                images.add(manUrl);
                images.add(womenUrl);
            }
        }
        return images;
    }

    public PersonImageProvider() {
        super(getImages());
    }
}

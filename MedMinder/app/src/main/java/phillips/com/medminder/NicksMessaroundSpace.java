package phillips.com.medminder;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nickm on 10/31/2017.
 */


public class NicksMessaroundSpace {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    myRef . setValue("Hello, World!");
}

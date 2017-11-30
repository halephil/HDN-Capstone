package phillips.com.medminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dave on 10/24/2017.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String intent_string = intent.getExtras().getString("extra");

        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        service_intent.putExtra("extra", intent_string);

        context.startService(service_intent);
    }
}

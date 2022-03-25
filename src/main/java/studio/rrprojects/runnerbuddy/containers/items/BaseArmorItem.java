package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONObject;
import studio.rrprojects.util_library.JSONUtil;

public class BaseArmorItem extends Buyable {
    String impactRating;
    String ballisticRating;

    public BaseArmorItem(String name) {
        super(name);
    }

    public BaseArmorItem() {
        super();
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);

        impactRating = JSONUtil.getString(object, "impact",  "0");
        ballisticRating = JSONUtil.getString(object, "ballistic", "0");
    }

    @Override
    public BaseArmorItem createNewFromJson(String name, JSONObject jsonObject) {
        BaseArmorItem baseArmorItem = new BaseArmorItem(name);
        baseArmorItem.ProcessJson(jsonObject);

        return baseArmorItem;
    }

    @Override
    public Buyable createNewFromJson() {
        return createNewFromJson(getName(), getJsonObject());
    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    public String getDescription() {

        return super.getDescription() + "\n\n" +
                " === Armor STATS ===\n" +
                "Ballistic Rating: " +  ballisticRating+ "\n" +
                "Impact Rating: " + impactRating;
    }
}

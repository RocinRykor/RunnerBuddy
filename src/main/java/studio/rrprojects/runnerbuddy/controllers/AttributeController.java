package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.priority.PointPriority;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;
import studio.rrprojects.runnerbuddy.gui.cards.attributes.Attributes;
import studio.rrprojects.runnerbuddy.gui.cards.attributes.AttributeModule;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttributeController extends ControllerClass {
    private final CharacterContainer characterContainer;
    private final LinkedHashMap<String, Double> basePointsMap;
    private int maxAttributePoints;
    private Attributes attributeCard;
    private LinkedHashMap<String, AttributeModule> attributeMap;
    private PointPriority selectedPriority;

    public AttributeController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        
        basePointsMap = new LinkedHashMap<>();
    }

    @Override
    public void setSelectedPriority(PriorityContainer priorityContainer) {
        selectedPriority = (PointPriority) priorityContainer;
        
        maxAttributePoints = selectedPriority.getPointValue();
    }

    public void LinkCard(Attributes attributeCard) {
        this.attributeCard = attributeCard;
    }

    @Override
    public String ValidCheck() {
        attributeCard.Update();
        attributeCard.UpdateAll();

        int allocatedPoints = 0;

        for (Map.Entry<String, AttributeModule> attribute: attributeMap.entrySet()) {
            String attributeName = attribute.getKey();

            //REA, ESS. and MAG like to get an allocated value of 1 which is wrong and so a check here is easier
            if (attributeName.equalsIgnoreCase("Reaction") ||
                    attributeName.equalsIgnoreCase("Essence") ||
                    attributeName.equalsIgnoreCase("Magic"))
            {
                break;
            }

            allocatedPoints += attribute.getValue().getAllottedPoints();

            //System.out.println("Running Total: " + attributeName + " -> " + allocatedPoints);
        }

        if (allocatedPoints > maxAttributePoints) {
            return "Error - Too many attribute points allocated";
        } else if (allocatedPoints < maxAttributePoints) {
            return "Caution - Unallocated attribute points, if you continue those points will be lost";
        } else {
            return "Valid";
        }

    }

    public void setAttributeMap(LinkedHashMap<String, AttributeModule> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public LinkedHashMap<String, AttributeModule> getAttributeMap() {
        return attributeMap;
    }

    public int getMaxAttributePoints() {
        return maxAttributePoints;
    }
}

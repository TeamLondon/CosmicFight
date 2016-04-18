package interfaces;

import interfaces.core.Database;
import models.contracts.ConfirmBox;
import models.contracts.MessageBox;

public interface StageController {
    void initialize(StageManager stageManager, Database database, MessageBox messageBox, ConfirmBox confirmBox);
}

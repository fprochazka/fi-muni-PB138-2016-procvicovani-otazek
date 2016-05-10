package com.fprochazka.drill.api.drill;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
public class DrillController {

    /**
     *  Function, that gives list of all drills in database.
     *
     *  @return drills - ArrayList of type Drill
     */
    @RequestMapping(value="/drill", method= RequestMethod.GET)
    public @ResponseBody Collection<Object> getAllDrills() {
        List<Object> drills = new ArrayList<Object>();
        // get all drills from DB by calling model layer
        return drills;
    }

    /**
     * Function, that returns drill in databse with given ID. If the ID is not given or do not corrspond to any drill in database, function returns null.
     *
     * @return drill - object of type Drill with given id, null if drill with the id does not exist
     */
    @RequestMapping(value="/drill/{drillId}", method= RequestMethod.POST)
    public @ResponseBody Object getDrillById() {
        // call model layer to get drill with given layer, do not check if drillId is null
        // if returned drill is null, give proper response to client, otherwise return given drill
        return null;
    }

    /**
     * Function sends signal to model layer to create new drill. Returns ok.
     *
     * @param name - name of new drill of type String
     */
    public void createDrill(@RequestBody String name) {
        // creates new drill with given name, let model layer generates new ID and save it to DB
    }
}

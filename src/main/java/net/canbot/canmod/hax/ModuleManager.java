package net.canbot.canmod.hax;

import net.canbot.canmod.hax.Module;
import net.canbot.canmod.hax.Modules.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static List<Module> modules = new ArrayList<>();

    public ModuleManager()  {

        modules.add(new Flight());
        modules.add(new boatfly());
        modules.add(new LiveOverflowBypass());
        modules.add(new NetherClip());
        modules.add(new SurvivalSpoof());
        modules.add(new NoBorder());
        modules.add(new PowderFloat());
    }

    public static List<Module> getModules(){
        return modules;
    }


    public static List<Module> getModulesInCategory(Module.Category c) {
        List<Module> modulesInCat = new ArrayList<>();
        for(Module m :  modules)  {
            if(m.getCategory() == c){
                modulesInCat.add(m);
            }
        }
        return modulesInCat;
    }
    public static Module getModulebyName(String module) {
        for(Module m : modules) {
            if(m.getName().equalsIgnoreCase(module)){
                return m;
            }
        }
        return null;
    }
}
package com.gendeathrow.cutscene.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.commons.compress.utils.IOUtils;

import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler 
{
	
	public static File configDir;
    static File configFolder;
    static File devFolder;
    
	public ConfigHandler(FMLPreInitializationEvent event)
	{
		this.configDir = event.getModConfigurationDirectory();
		configFolder = new File(configDir, "CustomCutScenes");
		devFolder = new File("custom-cutscene-files");
	}
	
	public void load()
	{

		
		    if (!configFolder.exists())
		    {
		    	configFolder.mkdir();
		    }
		    
		    loadConfigFile();
		    
		    
		    //TODO Readme is Temp for now
		      InputStream input = null;

		      OutputStream output = null;
		    try
		    {
		    	  File Readme = new File(configFolder, "README.txt"); 
		    	  output = new FileOutputStream(Readme);
		    	  input = getClass().getResourceAsStream("/assets/customcutscene/README.txt");
		    	  ByteStreams.copy(input, output);
		    }catch (FileNotFoundException e1)
		    {
		        e1.printStackTrace();
		    }
		    catch (IOException e)
		      {
		        e.printStackTrace();
		      }
		      finally
		      {
		        IOUtils.closeQuietly(output);
		        IOUtils.closeQuietly(input);
		      }
	        
	        
		    if(Settings.dev_Enviroment && !devFolder.exists())
		    {
		    	devFolder.mkdir();
		    }
		    
		    File splashScreenConfig = new File(configFolder, "splashscreen.json");
		    
		    if (!splashScreenConfig.exists() && Settings.dev_Enviroment)
		    {
		      input = null;

		      output = null;
		      try
		      {
		        output = new FileOutputStream(new File(configFolder ,"login.json"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/login_default.json");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(splashScreenConfig);
		        input = getClass().getResourceAsStream("/assets/customcutscene/splashscene_default.json");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "logo.png"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/logo.png");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "en_US.lang"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/en_US.lang");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "sunset.png"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/sunset.png");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "build.png"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/build.png");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "destory.png"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/destory.png");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "world.png"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/world.png");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "fire.gif"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/fire.gif");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "Dead_Reckoning.ogg"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/Dead_Reckoning.ogg");
		        ByteStreams.copy(input, output);
		        
		        output = new FileOutputStream(new File(devFolder, "Extinction_Level_Event.ogg"));
		        input = getClass().getResourceAsStream("/assets/customcutscene/Extinction_Level_Event.ogg");
		        ByteStreams.copy(input, output);
		      }
		      catch (FileNotFoundException e1)
		      {
		        e1.printStackTrace();
		      }
		      catch (IOException e)
		      {
		        e.printStackTrace();
		      }
		      finally
		      {
		        IOUtils.closeQuietly(output);
		        IOUtils.closeQuietly(input);
		      }
		    }
	}
	
	public void loadConfigFile()
	{
		Configuration config;
		
		config = new Configuration(new File(configFolder , "Custom_CutScenes.cfg"), "1.0");
		
		config.load();
		
		Settings.dev_Enviroment = config.get("Developers", "isDevelopersVersion", true, "If true. It will create your custom folders to add all of you assets").getBoolean();

		Property reload = config.get("Developers", "Reload_Assets_On_Load", true, "This will recreate the custom jar file when the game starts(set to false after you have your mod pack finished and your custom files jar created.)"+ config.NEW_LINE + " This will automatically set to false after you generate your new custom files");
			Settings.update_resources = reload.getBoolean();
		reload.set(false);

		Settings.trigger_OnPlayer_Login = config.get("Triggers", "On_Player_Login", Settings.trigger_OnPlayer_Login, "If true. This will Trigger when a player Logs in. (!Beware!: As of now this happens everytime)").getBoolean();
		Settings.file_OnPlayer_Login = config.get("Triggers", "On_Player_Login_File", Settings.file_OnPlayer_Login , "Type name of Json you want to load Example: \"filename.json\"").getString();
		Settings.trigger_OnPlayer_Login_Always = config.get("Triggers", "On_Player_Login_Always_Play", Settings.trigger_OnPlayer_Login_Always, "If true. This will play everytime the player logs into a world. Still Will only trigger once per game. You have to ").getBoolean();		
		// Register Triggers to json files
		
		// SplashScreen
		
		config.save();
	}
	
}

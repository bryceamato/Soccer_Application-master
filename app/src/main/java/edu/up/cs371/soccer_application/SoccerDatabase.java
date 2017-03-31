package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB
{
    Hashtable<String, SoccerPlayer> playerTable = new Hashtable<String, SoccerPlayer>();


    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            return false;
        }else
        {
            SoccerPlayer thisPlayer = new SoccerPlayer(firstName, lastName,
                    uniformNumber, teamName);
            playerTable.put(name, thisPlayer);
            return true;
        }
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            playerTable.remove(name);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            return playerTable.get(name);
        }
        else
        {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpGoals();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpAssists();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpShots();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpSaves();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpFouls();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpYellowCards();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName)
    {
        String name = firstName + "##" + lastName;
        if(playerTable.containsKey(name))
        {
            SoccerPlayer ourPlayer = playerTable.get(name);
            ourPlayer.bumpRedCards();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName)
    {
        int num = 0;
        if(teamName == null)
        {
            return playerTable.size();
        }
        else
        {
            Set<String> keys = playerTable.keySet();
            for(String key : keys)
            {
                if(playerTable.get(key).getTeamName().equals(teamName))
                {
                    ++num;
                }
            }
            return num;
        }
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        if (idx >= playerTable.size())
        {
            return null;
        } else {
            if (teamName == null) {
                ArrayList<SoccerPlayer> noTeam = new ArrayList<>();
                Set<String> keys = playerTable.keySet();
                for (String key : keys) {
                    noTeam.add(playerTable.get(key));

                }
                return noTeam.get(idx);
            } else {
                ArrayList<SoccerPlayer> teamPlayers = new ArrayList<>();
                Set<String> keys = playerTable.keySet();
                for (String key : keys) {
                    if (playerTable.get(key).getTeamName().equals(teamName)) {
                        teamPlayers.add(playerTable.get(key));
                    }
                }
                return teamPlayers.get(idx);
            }
        }
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file)
    {
        return false;
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file)
    {

        try{

            PrintWriter writer = new PrintWriter(file);
            Set<String> keys = playerTable.keySet();
            for(String key : keys)
            {
                SoccerPlayer player = playerTable.get(key);
                writer.println(logString(player.getFirstName() + ", " +  player.getLastName() + ", " +
                    player.getTeamName() + "," + player.getUniform() + "," + player.getGoals() + "," +
                    player.getAssists() + "," + player.getShots() + "," + player.getSaves() + "," +
                    player.getFouls() + "," + player.getYellowCards() + "," + player.getRedCards()));
            }
            writer.close();
            return true;
        } catch (IOException e)
        {
            // do something
            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        //Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams()
    {
        HashSet<String> teams = new HashSet<>();
        Set<String> keys = playerTable.keySet();
        for(String key : keys)
        {
            SoccerPlayer player = playerTable.get(key);
            String team = player.getTeamName();
            if(teams.contains(team))
            {
                //
            }else
            {
                teams.add(team);
            }
        }
        return teams;
	}

}

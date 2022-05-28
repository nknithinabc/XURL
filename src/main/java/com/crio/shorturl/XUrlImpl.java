package com.crio.shorturl;

import java.util.HashMap;

public class XUrlImpl implements XUrl
{
    //private int hit = 0;

    public HashMap<String,String> long_to_short = new HashMap<String,String>();
    public HashMap<String,String> short_to_long = new HashMap<String,String>();
    public HashMap<String,Integer> hit_count    = new HashMap<String,Integer>();

    public String registerNewUrl(String longUrl)
    {
        if(long_to_short.containsKey(longUrl))
        return long_to_short.get(longUrl);

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"
        + "abcdefghijklmnopqrstuvxyz";
									
		StringBuilder sb = new StringBuilder(9);

        for (int i = 0; i < 9; i++) 
        {
			int index = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

        String temp;
        String short_url;
        temp = sb.toString();

        short_url = "http://short.url/"+temp;

        long_to_short.put(longUrl,short_url);
        short_to_long.put(short_url,longUrl);
        //System.out.print(short_url);
        return short_url;
    }

    public String getUrl(String shortUrl)
    {
        String temp;
        temp = shortUrl;

        if(!short_to_long.containsKey(temp))
        return null;

        String ans;
        ans = short_to_long.get(temp);

        if(!hit_count.containsKey(ans))
        {
            hit_count.put(ans,1);
        }
        else
        {
            hit_count.put(ans,hit_count.get(ans)+1);
        }

        return ans; 

    }

    public String delete(String longUrl)
    {
        String ans = long_to_short.get(longUrl);

        short_to_long.remove(ans);
        long_to_short.remove(longUrl);
        
        return null ;
    }


    public String registerNewUrl(String longUrl, String shortUrl)
    {
        if(short_to_long.containsKey(shortUrl))
        return null;

        long_to_short.put(longUrl,shortUrl);
        short_to_long.put(shortUrl,longUrl);
        return shortUrl;
    }

    public Integer getHitCount(String longUrl)
    {
        if(!hit_count.containsKey(longUrl))
        return 0;

        return hit_count.get(longUrl);
    }
    
   
}
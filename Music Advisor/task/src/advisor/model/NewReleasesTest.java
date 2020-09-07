package advisor.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NewReleasesTest {
    public static String newRlTestData = "{\n" +
            "    \"albums\": {\n" +
            "        \"href\": \"https://api.spotify.com/v1/browse/new-releases?offset=0&limit=20\",\n" +
            "        \"items\": [\n" +
            "            {\n" +
            "                \"album_type\": \"single\",\n" +
            "                \"artists\": [\n" +
            "                    {\n" +
            "                        \"external_urls\": {\n" +
            "                            \"spotify\": \"https://open.spotify.com/artist/2RdwBSPQiwcmiDo9kixcl8\"\n" +
            "                        },\n" +
            "                        \"href\": \"https://api.spotify.com/v1/artists/2RdwBSPQiwcmiDo9kixcl8\",\n" +
            "                        \"id\": \"2RdwBSPQiwcmiDo9kixcl8\",\n" +
            "                        \"name\": \"Pharrell Williams\",\n" +
            "                        \"type\": \"artist\",\n" +
            "                        \"uri\": \"spotify:artist:2RdwBSPQiwcmiDo9kixcl8\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"available_markets\": [\n" +
            "                    \"AD\"\n" +
            "                ],\n" +
            "                \"external_urls\": {\n" +
            "                    \"spotify\": \"https://open.spotify.com/album/5ZX4m5aVSmWQ5iHAPQpT71\"\n" +
            "                },\n" +
            "                \"href\": \"https://api.spotify.com/v1/albums/5ZX4m5aVSmWQ5iHAPQpT71\",\n" +
            "                \"id\": \"5ZX4m5aVSmWQ5iHAPQpT71\",\n" +
            "                \"images\": [\n" +
            "                    {\n" +
            "                        \"height\": 640,\n" +
            "                        \"url\": \"https://i.scdn.co/image/e6b635ebe3ef4ba22492f5698a7b5d417f78b88a\",\n" +
            "                        \"width\": 640\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"height\": 300,\n" +
            "                        \"url\": \"https://i.scdn.co/image/92ae5b0fe64870c09004dd2e745a4fb1bf7de39d\",\n" +
            "                        \"width\": 300\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"height\": 64,\n" +
            "                        \"url\": \"https://i.scdn.co/image/8a7ab6fc2c9f678308ba0f694ecd5718dc6bc930\",\n" +
            "                        \"width\": 64\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"name\": \"Runnin'\",\n" +
            "                \"type\": \"album\",\n" +
            "                \"uri\": \"spotify:album:5ZX4m5aVSmWQ5iHAPQpT71\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"album_type\": \"single\",\n" +
            "                \"artists\": [\n" +
            "                    {\n" +
            "                        \"external_urls\": {\n" +
            "                            \"spotify\": \"https://open.spotify.com/artist/3TVXtAsR1Inumwj472S9r4\"\n" +
            "                        },\n" +
            "                        \"href\": \"https://api.spotify.com/v1/artists/3TVXtAsR1Inumwj472S9r4\",\n" +
            "                        \"id\": \"3TVXtAsR1Inumwj472S9r4\",\n" +
            "                        \"name\": \"Drake2\",\n" +
            "                        \"type\": \"artist\",\n" +
            "                        \"uri\": \"spotify:artist:3TVXtAsR1Inumwj472S9r4\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"available_markets\": [\n" +
            "                    \"AD\"\n" +
            "                ],\n" +
            "                \"external_urls\": {\n" +
            "                    \"spotify\": \"https://open.spotify.com/album/0geTzdk2InlqIoB16fW9Nd\"\n" +
            "                },\n" +
            "                \"href\": \"https://api.spotify.com/v1/albums/0geTzdk2InlqIoB16fW9Nd\",\n" +
            "                \"id\": \"0geTzdk2InlqIoB16fW9Nd\",\n" +
            "                \"images\": [\n" +
            "                    {\n" +
            "                        \"height\": 640,\n" +
            "                        \"url\": \"https://i.scdn.co/image/d40e9c3d22bde2fbdb2ecc03cccd7a0e77f42e4c\",\n" +
            "                        \"width\": 640\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"height\": 300,\n" +
            "                        \"url\": \"https://i.scdn.co/image/dff06a3375f6d9b32ecb081eb9a60bbafecb5731\",\n" +
            "                        \"width\": 300\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"height\": 64,\n" +
            "                        \"url\": \"https://i.scdn.co/image/808a02bd7fc59b0652c9df9f68675edbffe07a79\",\n" +
            "                        \"width\": 64\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"name\": \"Sneakin'\",\n" +
            "                \"type\": \"album\",\n" +
            "                \"uri\": \"spotify:album:0geTzdk2InlqIoB16fW9Nd\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"limit\": 20,\n" +
            "        \"next\": null,\n" +
            "        \"offset\": 0,\n" +
            "        \"previous\": null,\n" +
            "        \"total\": 2\n" +
            "    }\n" +
            "}";

   public static String featuredTestData = "{\n" +
           "    \"message\": \"Monday morning music, coming right up!\",\n" +
           "    \"playlists\": {\n" +
           "        \"href\": \"https://api.spotify.com/v1/browse/featured-playlists?offset=0&limit=20\",\n" +
           "        \"items\": [\n" +
           "            {\n" +
           "                \"collaborative\": false,\n" +
           "                \"external_urls\": {\n" +
           "                    \"spotify\": \"http://open.spotify.com/user/spotify/playlist/6ftJBzU2LLQcaKefMi7ee7\"\n" +
           "                },\n" +
           "                \"href\": \"https://api.spotify.com/v1/users/spotify/playlists/6ftJBzU2LLQcaKefMi7ee7\",\n" +
           "                \"id\": \"6ftJBzU2LLQcaKefMi7ee7\",\n" +
           "                \"images\": [\n" +
           "                    {\n" +
           "                        \"height\": 300,\n" +
           "                        \"url\": \"https://i.scdn.co/image/7bd33c65ebd1e45975bbcbbf513bafe272f033c7\",\n" +
           "                        \"width\": 300\n" +
           "                    }\n" +
           "                ],\n" +
           "                \"name\": \"Monday Morning Mood\",\n" +
           "                \"owner\": {\n" +
           "                    \"external_urls\": {\n" +
           "                        \"spotify\": \"http://open.spotify.com/user/spotify\"\n" +
           "                    },\n" +
           "                    \"href\": \"https://api.spotify.com/v1/users/spotify\",\n" +
           "                    \"id\": \"spotify\",\n" +
           "                    \"type\": \"user\",\n" +
           "                    \"uri\": \"spotify:user:spotify\"\n" +
           "                },\n" +
           "                \"public\": null,\n" +
           "                \"snapshot_id\": \"WwGvSIVUkUvGvqjgj/bQHlRycYmJ2TkoIxYfoalWlmIZT6TvsgvGMgtQ2dGbkrAW\",\n" +
           "                \"tracks\": {\n" +
           "                    \"href\": \"https://api.spotify.com/v1/users/spotify/playlists/6ftJBzU2LLQcaKefMi7ee7/tracks\",\n" +
           "                    \"total\": 245\n" +
           "                },\n" +
           "                \"type\": \"playlist\",\n" +
           "                \"uri\": \"spotify:user:spotify:playlist:6ftJBzU2LLQcaKefMi7ee7\"\n" +
           "            },\n" +
           "            {\n" +
           "                \"collaborative\": false,\n" +
           "                \"external_urls\": {\n" +
           "                    \"spotify\": \"http://open.spotify.com/user/spotify__sverige/playlist/4uOEx4OUrkoGNZoIlWMUbO\"\n" +
           "                },\n" +
           "                \"href\": \"https://api.spotify.com/v1/users/spotify__sverige/playlists/4uOEx4OUrkoGNZoIlWMUbO\",\n" +
           "                \"id\": \"4uOEx4OUrkoGNZoIlWMUbO\",\n" +
           "                \"images\": [\n" +
           "                    {\n" +
           "                        \"height\": 300,\n" +
           "                        \"url\": \"https://i.scdn.co/image/24aa1d1b491dd529b9c03392f350740ed73438d8\",\n" +
           "                        \"width\": 300\n" +
           "                    }\n" +
           "                ],\n" +
           "                \"name\": \"Upp och hoppa!\",\n" +
           "                \"owner\": {\n" +
           "                    \"external_urls\": {\n" +
           "                        \"spotify\": \"http://open.spotify.com/user/spotify__sverige\"\n" +
           "                    },\n" +
           "                    \"href\": \"https://api.spotify.com/v1/users/spotify__sverige\",\n" +
           "                    \"id\": \"spotify__sverige\",\n" +
           "                    \"type\": \"user\",\n" +
           "                    \"uri\": \"spotify:user:spotify__sverige\"\n" +
           "                },\n" +
           "                \"public\": null,\n" +
           "                \"snapshot_id\": \"0j9Rcbt2KtCXEXKtKy/tnSL5r4byjDBOIVY1dn4S6GV73EEUgNuK2hU+QyDuNnXz\",\n" +
           "                \"tracks\": {\n" +
           "                    \"href\": \"https://api.spotify.com/v1/users/spotify__sverige/playlists/4uOEx4OUrkoGNZoIlWMUbO/tracks\",\n" +
           "                    \"total\": 38\n" +
           "                },\n" +
           "                \"type\": \"playlist\",\n" +
           "                \"uri\": \"spotify:user:spotify__sverige:playlist:4uOEx4OUrkoGNZoIlWMUbO\"\n" +
           "            }\n" +
           "        ],\n" +
           "        \"limit\": 20,\n" +
           "        \"next\": null,\n" +
           "        \"offset\": 0,\n" +
           "        \"previous\": null,\n" +
           "        \"total\": 2\n" +
           "    }\n" +
           "}";

   public static JsonObject joTestNewRl = new JsonParser().parse(newRlTestData).getAsJsonObject();
   public static JsonObject joTestNewfeat = new JsonParser().parse(featuredTestData).getAsJsonObject();







}

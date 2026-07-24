package com.sddevops.jenkins_maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongCollectionTest {

    private static final int SONG_COLLECTION_SIZE = 4;

    private SongCollection sc;
    private Song s1;
    private Song s2;
    private Song s3;
    private Song s4;

    @BeforeEach
    void setUp() {
        // Arrange
        sc = new SongCollection();

        s1 = new Song("001", "good 4 u", "Olivia Rodrigo", 3.59);
        s2 = new Song("002", "Peaches", "Justin Bieber", 3.18);
        s3 = new Song("003", "MONTERO", "Lil Nas", 2.30);
        s4 = new Song("004", "bad guy", "billie eilish", 3.14);

        sc.addSong(s1);
        sc.addSong(s2);
        sc.addSong(s3);
        sc.addSong(s4);
    }

    @Test
    void testSongCollectionCapacity() {
        SongCollection collection = new SongCollection(2);

        collection.addSong(new Song("005", "Song A", "Artist A", 3.00));
        collection.addSong(new Song("006", "Song B", "Artist B", 4.00));
        collection.addSong(new Song("007", "Song C", "Artist C", 5.00));

        assertEquals(2, collection.getSongs().size());
    }

    @Test
    void testGetSongs() {
        List<Song> testSc = sc.getSongs();

        assertNotNull(testSc);
        assertEquals(SONG_COLLECTION_SIZE, testSc.size());
        assertEquals(s1, testSc.get(0));
        assertEquals(s2, testSc.get(1));
        assertEquals(s3, testSc.get(2));
        assertEquals(s4, testSc.get(3));
    }

    @Test
    void testAddSong() {
        List<Song> testSc = sc.getSongs();

        assertEquals(SONG_COLLECTION_SIZE, testSc.size());

        sc.addSong(new Song("005", "New Song", "New Artist", 4.00));

        assertEquals(SONG_COLLECTION_SIZE + 1, testSc.size());
    }

    @Test
    void testSortSongsByTitle() {
        List<Song> sortedSongList = sc.sortSongsByTitle();

        assertEquals("MONTERO", sortedSongList.get(0).getTitle());
        assertEquals("Peaches", sortedSongList.get(1).getTitle());
        assertEquals("bad guy", sortedSongList.get(2).getTitle());
        assertEquals("good 4 u", sortedSongList.get(3).getTitle());
    }

    @Test
    void testSortSongsBySongLength() {
        List<Song> sortedSongByLengthList = sc.sortSongsBySongLength();

        assertEquals(3.59, sortedSongByLengthList.get(0).getSongLength(), 0.001);
        assertEquals(3.18, sortedSongByLengthList.get(1).getSongLength(), 0.001);
        assertEquals(3.14, sortedSongByLengthList.get(2).getSongLength(), 0.001);
        assertEquals(2.30, sortedSongByLengthList.get(3).getSongLength(), 0.001);
    }

    @Test
    void testFindSongsById() {
        Song song = sc.findSongsById("002");

        assertNotNull(song);
        assertEquals("Justin Bieber", song.getArtiste());
    }

    @Test
    void testFindSongByTitle() {
        Song result = sc.findSongByTitle("bad guy");

        assertNotNull(result);
        assertEquals("004", result.getId());
    }

    @Test
    void testFindSongsByIdNotFound() {
        assertNull(sc.findSongsById("999"));
    }

    @Test
    void testFindSongByTitleNotFound() {
        assertNull(sc.findSongByTitle("Unknown Song"));
    }

    @Test
    void testSmallCollectionCapacityConstructor() {
        SongCollection smallCollection = new SongCollection(2);

        smallCollection.addSong(new Song("005", "A", "Artist A", 3.00));
        smallCollection.addSong(new Song("006", "B", "Artist B", 3.10));
        smallCollection.addSong(new Song("007", "C", "Artist C", 3.20));

        assertEquals(2, smallCollection.getSongs().size());
    }

}
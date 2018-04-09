package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest(){
        //given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(
                new TrelloBoardDto("BoardNoOne", "001", trelloListDtos),
                new TrelloBoardDto("BoardNoTwo", "002", trelloListDtos),
                new TrelloBoardDto("BoardNoThree", "003", trelloListDtos)
        );
        //when
        List<TrelloBoard> boards = trelloMapper.mapToBoards(trelloBoardDtos);
        //then
        assertEquals(3,boards.size());
    }

    @Test
    public void mapToBoardDtoTest(){
        //given
        List<TrelloList> trelloList = new ArrayList<>();
        List<TrelloBoard> trelloBoard = Arrays.asList(
                new TrelloBoard("BoardNoOne", "001", trelloList),
                new TrelloBoard("BoardNoTwo", "002", trelloList),
                new TrelloBoard("BoardNoThree", "003", trelloList)
        );
        //when
        List<TrelloBoardDto> boards = trelloMapper.mapToBoardDto(trelloBoard);
        //then
        assertEquals(3,boards.size());
    }

    @Test
    public void mapToListTest(){
        //given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("ListNoOne", "001", true),
                new TrelloListDto("ListNoTwo", "002", false),
                new TrelloListDto("ListNoThree", "003", false)
        );
        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //then
        assertEquals(3, trelloLists.size());
    }

    @Test
    public void mapToListDtoTest(){
       //given
        List<TrelloList> trelloList = Arrays.asList(
                new TrelloList("ListNoOne", "001", true),
                new TrelloList("ListNoTwo", "002", false),
                new TrelloList("ListNoThree", "003", false)
        );
        //when
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(trelloList);
        //then
        assertEquals(3, trelloLists.size());
    }

    @Test
    public void mapToCardTest() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("My test card", "Testing card", "top", "001");
        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //then
        assertEquals("My test card", trelloCard.getName());
        assertEquals("Testing card", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("001", trelloCard.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //given
        TrelloCard trelloCard = new TrelloCard("My test card", "Testing card", "top", "001");
        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //then
        assertEquals("My test card", trelloCardDto.getName());
        assertEquals("Testing card", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("001", trelloCardDto.getListId());
    }

}
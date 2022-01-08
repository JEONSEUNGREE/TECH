//package com.example.SpringBatch;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class ChunkOrientedTasklet {
//
//    /*
//    Chunk기반이기에 ItemReader, ItemWriter, ItemProcessor가 존재하여 데이터 입출력 처리를 담당함
//    TaskletStep에 의해서 반복실행되며 ChunkOrientedTasklet이 실행 될때 마다 새로운 트랜잭션이 생성되어 처리가 이루어진다.
//    예외 발생시 Chunk가 롤백되며 이전 Chunk 완료상태는 유지됨
//
//    ChunkOrientedTasklet -> ChunkProvider를 통해서 -> ChunkProcessor -> ItemReader 전달 -> ItemProcessor (ItemReader 전달한 갯수만큼 반복) -> ItemWriter
//
//    * */
//}

import { useRouter } from "next/router";

// our-domain/news url에 반응하여 new.js파일을 보여줌
// index는 / 홈에 해당
// NEXTJS는 import React가 (생략되어있음 자동지원)
// 파일명 [ ] 표시로 매개변수를 받을수있음 (동적으로사용)
function DetailPage() {
  const router = useRouter();

  console.log(router.query.newsId) ;

  return <h1>The News Page</h1>;
}

export default DetailPage;

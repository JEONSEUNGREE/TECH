const { User } = require("../models/User")

let auth = (req, res, next) => {

  // 인증 처리를 하는 곳

  // 클라이언트 쿠키에서 토큰을 가져온다. cookieParser 라이브러리
  let token = req.cookies.x_auth;

  // 토큰을 복호화 한후 유저를 찾는다.
  User.findByToken(token, (err, user) => {
    if (err) throw err ;
    if (!user) return res.json( { isAuth: false, error: true } );

    req.token = token;
    req.user = user;
    //   middleware이기때문에 다음 진행 유도
    next();
  })

  // 유저가 있으면 인증O

  // 유저가 없으면 인증X

}

module.exports = { auth }
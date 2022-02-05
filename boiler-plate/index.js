const express = require('express')
const app = express()
const port = 5000

app.get('/', (req, res) => {
  res.send('서버 구동하기!')
})

const mongoose = require("mongoose")
mongoose.connect("mongodb+srv://seungree:<password>@youtubereact.h0vel.mongodb.net/myFirstDatabase?retryWrites=true&w=majority", {
    // useNewUrlParser: true, useUnifiedTopology: true, useCreateIndex: true, useFindAndModify: false
    // 몽구스 6.0부터 위 옵션을 적지않아도 기본값 적용됨
}).then( () => console.log("MongoDB Connected...."))
  .catch(err => console.log(err))

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
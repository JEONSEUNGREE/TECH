const mongoose = require("mongoose");

const { hash } = require("bcrypt");

const bcrypt = require('bcrypt');
const saltRounds = 10;

const jwt = require('jsonwebtoken');

const userSchema = mongoose.Schema({
    name: {
        type: String,
        maxlength: 50
    },

    email: {
        type: String,
        trim: true,
        unique: 1
    },

    password: {
        type: String,
        minlength: 5
    },

    lastname: {
        type: String,
        maxlength: 50
    },

    role: {
        type: Number,
        default: 0
    },

    image: String,

    token: {
        type: String
    },

    tokenExp: {
        type: Number
    },
})

// save 메서드 실행전에 전처리 작업
userSchema.pre("save", function( next ) {
    let user = this;
    // 비밀번호 암호화

    // 비밀번호 바꿀때만 암호화
    if(user.isModified("password")) {
        // salt 생성 saltRound(암호화갯수)
        bcrypt.genSalt(saltRounds, function (err, salt) {
            // 에러시 바로 save메서드 실행
            if (err) return next(err)

            bcrypt.hash(user.password, salt, function (err, hash) {
                if (err) return next(err)
                user.password = hash
                next()
            })
        })
    } else {
        next()
    }
})
// cb = callback
userSchema.methods.comparePassword = function(plainPassword, cb) {

    // plainPassword  암호화된 비밀번호
    bcrypt.compare(plainPassword, this.password, function(err, isMatch) {
        if(err) {return cb(err)}
        // 에러는 null, isMatch(boolean) 라는 뜻
        cb(null, isMatch)
    })
}

userSchema.methods.generateToken = function(cb) {

    let user = this;
    // jsonwebtoken을 이용해서 token을 생성
    let token = jwt.sign(user._id.toHexString(), "secretToken")
    // user._id + secretToken = token 생성

    user.token = token
    user.save(function(err, user) {
        if(err) return cb(err)
        cb(null, user)
    })

}

const User = mongoose.model("User", userSchema)

module.exports = { User }


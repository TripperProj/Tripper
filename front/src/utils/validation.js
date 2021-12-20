function validateEmail(email) {
  //이메일 형식 확인
  const re =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}
function validatePassword(password) {
  //비밀번호 형식 확인
  const re = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
  return re.test(String(password).toLowerCase());
}
function checkPassword(password, passwordCheck) {
  //비밀번호 확인
  return password === passwordCheck;
}

function validateImgFile(files) {
  for (let i = 0; i < files.length(); i++) {
    if (files[i] > 1024 * 1024) {
      alert("IMG FILE IS TOO BIG");
      return false;
    } else {
      return true;
    }
  }
}
export { validateEmail, validatePassword, checkPassword, validateImgFile };

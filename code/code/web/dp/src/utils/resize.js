const initResize = function () {
  windowResize()
  window.onresize = (function () {
    let timer
    return function () {
      clearTimeout(timer)
      timer = setTimeout(() => {
        windowResize()
      }, 100)
    }
  })()
}
const windowResize = function () {
  const baseWidth = 1920
  const BaseFontSize = 100
  const width = document.body.clientWidth
  const ratio = width / baseWidth
  const fontSize = ratio * BaseFontSize
  document.documentElement.style.fontSize = fontSize + 'px'
}
const calcFontSize = function (num, min) {
  const size = (document.body.clientWidth / 1920) * num
  if (min && min > size) {
    return min
  }
  return size
}
export { initResize, calcFontSize }

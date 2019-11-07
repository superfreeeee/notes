
(function() {
  const canvas = document.getElementById('board')
  const context = canvas.getContext('2d')
  const board = new Array(19).fill(0).map(() => new Array(19).fill(0))

  init()
  runtime()

  function init() {
    // set line style
    setStyle({
      strokeStyle: 'black',
      strokeWidth: 2,
    })

    for(let i=20 ; i<760 ; i+=40) {
      drawLine(i, 20, i, 740)
      drawLine(20, i, 740, i)
    }

    // set circle style
    setStyle({
      strokeStyle: 'black',
      strokeWidth: 1,
      fillStyle: 'black'
    })

    for(let i=0 ; i<3 ; i++) 
      for(let j=0 ; j<3 ; j++)
        drawCircle(140 + i * 240, 140 + j * 240, 5)
  }

  function runtime() {
    const groups1 = []
    const groups2 = []

    setMovement()
  }

  function setMovement() {
    const rect = canvas.getBoundingClientRect()
    // console.log(rect)
    // console.log(rect.left, rect.top)
    let current = 'black'
    let dx, dy

    setStyle({
      strokeWidth: 1,
      strokeStyle: 'black',
    })

    const put = (dx, dy) => {
      if(board[dy][dx] == 0) {
        setStyle({
          fillStyle: current,
        })
        board[dy][dx] = current === 'black' ? 1 : 2
        printboard()
        
        dx = 20 + dx * 40
        dy = 20 + dy * 40
        drawCircle(dx, dy, 15)
      }
      else
        console.log('already put!\nchoose another place')  
    }

    const remove = group => {

    }

    function check(dx, dy) {
      console.log(current)
      // const life = getLife(dx, dy)
      // console.log(life)
    }

    canvas.onclick = e => {
      // console.log(e)
      // console.log(e.clientX, e.clientY)
      [dx, dy] = [
        Math.floor((e.clientX - rect.left)/40), 
        Math.floor((e.clientY - rect.top)/40) 
      ]
      put(dx, dy)
      check(dx, dy)
      current = current === 'black' ? 'white' : 'black'
    }

    function getLife(dx, dy) {
      if(dx < 0 || dx > 18 || dy < 0 || dy > 18)
        return 0
      if(board[dx][dy])
      return getLife(dx+1, dy) + getLife(dx, dy+1) + getLife(dx-1, dy) + getLife(dx, dy-1)
    }
    
    function printboard() {
      let result = ''
      for(let i=0 ; i<19 ; i++) {
        for(let j=0 ; j<19 ; j++) {
          if(board[i][j] == 0)
            result += '. '
          else
            result += board[i][j] + ' '
        }
        result += '\n'
      }
      console.log(result)
      console.log(`player ${current === 'black' ? 1: 2} put at (${dy+1}, ${dx+1})`)
    }
  }

  function setStyle(style) {
    for(let attr in style)
      context[attr] = style[attr]  
  }

  function drawLine(fromX, fromY, toX, toY) {
    for(let i=0 ; i<10 ; i++) {
      context.beginPath()
      context.moveTo(fromX, fromY)
      context.lineTo(toX, toY)
      context.stroke()
    }
  }

  function drawCircle(centerX, centerY, radius) {
    context.beginPath()
    context.arc(centerX, centerY, radius, 0, Math.PI*2)
    context.fill()
    context.stroke()
  }
  
}())


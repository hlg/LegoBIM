import processing.core.PApplet
import ddf.minim.AudioSample
import ddf.minim.Minim

class LegoBIM extends PApplet {

  def data1 = [Ext_0_0: [-30, 290],
          Ext_0_1: [290, 1040],
          Ext_0_2: [430, 1215],
          Ext_02: [625, 720],
          Ext_0_3: [1100, 1425],
          Sta_2: [1150, 1195],
          Ext_0_4: [1275, 1630],
          Sta_0: [1295, 1345],
          Sta_1: [1345, 1360],
          Ext_1_0: [1630, 2100],
          Ext_1_1: [1820, 3495],
          Ext_1_5: [2100, 2370],
          Ext_1_4: [2370, 2825],
          Ext_1_3: [2590, 2955],
          Ext_1_2: [2725, 3330]]

  def data2 = [
          Slab_0: [-30, 140],
          Slab_1: [140, 1110],
          Int_0_0: [360, 870],
          Int_0_1: [380, 780],
          Int_0_2: [400, 810],
          Roof: [1110, 1850],
          Int_1_0: [1850, 2370],
          Int_1_2: [2100, 3350],
          Int_1_3: [2130, 2470],
          Int_1_1: [2630, 3170]
  ]

  def milestones1 = [190, 355, 400, 465, 505, 575, 625, 720, 915, 1000, 1040, 1150, 1175, 1195, 1295, 1320, 1345, 1360, 1485, 1630, 1735, 1880, 2000, 2160, 2235, 2290, 2510, 2670, 2725, 2820, 3055, 3330, 3495]
  def milestones2 = [0, 140, 260, 360, 470, 580, 720, 750, 870, 1020, 1110, 1610, 1850, 2010, 2130, 2210, 2630, 2830, 2970, 3350] // .collect{ it+30}

  def stepParts1 = [[3001: 34, 3003: 4], [3001: 32, 3003: 1], [3001: 9], [3001: 11, 3003: 2], [3003: 1, 3004: 2, 3001: 5], [3001: 13, 3003: 1],
          [3001: 4, 3003: 4, 3004: 2], [3001: 16, 3003: 3], [3001: 33, 3002: 2, 3003: 3, 3007: 1], [3004: 2, 3003: 2, 3001: 11, 2456: 1, 3006: 1],
          [3001: 7, 3007: 1], [3002: 4, 3001: 18], [3007: 1, 3002: 4], [3020: 4], [3002: 6, 3001: 11, 3010: 1, 3005: 2], [3002: 4, 3007: 1],
          [3020: 5], [3029: 1, 60479: 1, 3710: 1], [3001: 18, 3010: 1, 3005: 2, 3002: 4], [3004: 4, 3002: 7, 3001: 18], [3001: 20, 3002: 1],
          [3002: 8, 3001: 19, 3003: 2], [3002: 4, 3001: 16, 3003: 4], [3001: 31, 3003: 1], [3001: 9, 3002: 3, 3003: 3],
          [3002: 1, 3003: 3, 3006: 1, 3001: 6], [3001: 30, 3007: 1, 3004: 8, 3003: 2, 3002: 3], [3001: 25, 3002: 6, 3004: 1],
          [3001: 6, 3002: 1, 3003: 2, 3004: 2], [3001: 13, 3002: 3, 3003: 3], [3002: 9, 3001: 38], [3007: 1, 3001: 48, 3002: 3, 3003: 3],
          [3007: 2, 3004: 1, 3002: 1, 2456: 1, 3001: 24, 3003: 4]]

  def stepParts2 = [[3020: 1, 3029: 2], [3460: 2, 4282: 2, 92438: 8, 3027: 1, 3033: 1], [3029: 4, 3027: 1, 3033: 1, 3020: 2, 92438: 3, 4282: 1],
          [92438: 7, 4282: 2, 3027: 1], [3004: 1, 3005: 3, 3009: 1, 3008: 1, 2465: 2, 6112: 2, 3010: 1],
          [3004: 2, 3005: 2, 3008: 1, 3009: 2, 3010: 2, 2465: 1, 6112: 1], [3009: 8, 3622: 3, 3005: 3], [2465: 1, 6112: 1, 3008: 1],
          [3009: 3, 3008: 1, 3010: 1, 3005: 4, 2465: 1, 3004: 1, 6112: 1], [3832: 1, 3020: 2, 3710: 1, 92438: 6, 3027: 1, 3022: 1, 3029: 2, 3033: 1],
          [92438: 2, 4282: 1, 3033: 1, 3022: 1, 3832: 1, 3020: 1, 3710: 2],
          [60479: 1, 3460: 7, 3666: 2, 3031: 1, 3029: 3, 3028: 7, 41539: 3, 3036: 10, 3958: 3, 92438: 7, 3795: 1, 3710: 1, 3020: 1, 4282: 1, 3027: 1, 3033: 1],
          [92438: 9, 3832: 2, 3460: 4, 3795: 1, 3710: 1, 3666: 1, 3020: 1, 4282: 1, 3027: 1, 3029: 2, 3033: 1], [3010: 16],
          [3010: 7, 3622: 3, 3004: 2], [3004: 4, 3010: 3, 3622: 1], [3010: 11, 3004: 18, 3622: 9, 3009: 1, 3005: 3], [3010: 20],
          [3010: 8, 3005: 3, 3622: 3], [3010: 19, 3005: 13, 3009: 1, 3004: 1, 2465: 1, 3622: 3], [:]]

  Team team1
  Team team2

  AudioSample buzz
  boolean running = false
  boolean atStart = true
  boolean pause = false

  def tick = 0
  def time = -6
  def lastTime

  void setup() {
    size((int) screen.width * 2, (int) screen.height, P2D)
    textFont(createFont("Arial", 96), 24)
    buzz = new Minim(this).loadSample("Buzzer_.wav")
    def config = [barHeight: 10]
    team1 = new Team(
            activities: data1.collect { n, p -> new Activity(start: p[0].intdiv(5), end: p[1].intdiv(5), pa: this, config: config) },
            pa: this, color: color(0xC9, 0x1A, 9), mileStones: milestones1.collect {it.intdiv(5)}, no: 1, stepParts: stepParts1)
    team2 = new Team(
            activities: data2.collect { n, p -> new Activity(start: p[0].intdiv(5), end: p[1].intdiv(5), pa: this, config: config) },
            pa: this, color: color(0, 0x55, 0xBF), mileStones: milestones2.collect {it.intdiv(5)}, no: 2, stepParts: stepParts2)
    frameRate(2)
    lastTime = millis()
  }

  void draw() {
    background(255)
    translate(40, 100)
    team1.draw(running, time)
    translate(screenWidth, 0)
    team2.draw(running, time)
    if (running && !pause) {
      tick++
      if (tick == 10) {
        tick = 0
        time++
      }
    }

  }

  @Override
  void keyPressed() {
    if (key == 'p') {
      pause = !pause
    }
    if (!pause) {
      if (running) {
        if (key == 'a') team1.report(time)
        if (key == 'b') team2.report(time)
        if (key == 'b' || key == 'a') {
          buzz.trigger()
          running = (team1.working || team2.working)
        }
      } else {
        team1.start()
        team2.start()
        if (atStart) { // beginnung
          atStart = false
          running = true
        } else { // end
          time = -6
          tick = 0
          team1.reset()
          team2.reset()
          atStart = true
        }
      }
    }
  }

  public static void main(String[] args) {
    PApplet.main(["LegoBIM"] as String[])
  }
}

import processing.core.PApplet
import processing.core.PImage

class Team {

  def color
  List<Activity> activities
  PApplet pa
  boolean report
  boolean working = false
  int buzzing
  def reached = -1  // no of milestone
  PImage currentStep
  def partImages
  def mileStones
  def stepParts
  int no

  void draw(boolean running, int time){
    border(running)
    activities.eachWithIndex { act, i -> act.draw(i, time) }
    if (running) {
      if(working) drawStatus()
      if(working) drawStep()
    }
  }
  private void border(running) {
    pa.pushStyle()
    pa.fill(220)
    pa.stroke(color)
    pa.strokeWeight(2)
    pa.rect(-20, -20, 730, 400)
    if(running) pa.rect(730, -20, 1120, 900)
    pa.popStyle()
  }
  private void drawStatus() {
    pa.pushStyle()
    pa.fill(220)
    pa.stroke(color)
    pa.strokeWeight(2)
    def pos = mileStones[reached+1]
    pa.line(pos, 450, pos, -60)
    pa.line(pos, 450, 730, 450)
    pa.fill(color)
    def dia = 30
    if (report && buzzing < 1) {
      buzzing++
      dia = 50
    } else {
      buzzing = 0
      report = false
    }
    pa.ellipse(pos, -60, dia, dia)
    pa.popStyle()
  }

   void report(time){
     if(working){
       reached++
       report = true
       activities.each { it.reportMileStone(mileStones[reached]) }
       println "milestone $reached, current ${time*5}, expected ${mileStones[reached]*5} " // ${(time * 5 * 100).intdiv(mileStones[reached]*5)} %
       working = (reached < mileStones.size()-1)
       if (working) {
         partImages = stepParts[reached+1].collectEntries{ k,v -> [k, pa.loadImage("${k}_${no==1?'r':'b'}.png")] }
         currentStep = pa.loadImage("team_${no}_${reached+1}.png")
       }
     }
   }

  void start(){
    currentStep = pa.loadImage("team_${no}_0.png")
    working = true
    partImages = stepParts[0].collectEntries{ k,v -> [k, pa.loadImage("${k}_${no==1?'r':'b'}.png")] }

  }

  void reset(){
    reached = -1
    activities.each { it.reset() }
  }

  void drawStep(){
    partImages.eachWithIndex{ k,img,i ->
     def y = 500 + i.intdiv(2) * 90
     pa.image(img, i%2 ? 360 : 40, y)
     pa.fill(0)
     pa.text(stepParts[reached+1][k], i%2 ? 320 : 0, y+20)
   }
    pa.image(currentStep, pa.screen.width-100-currentStep.width as int, pa.screen.height - 150 -currentStep.height as int)

  }

}

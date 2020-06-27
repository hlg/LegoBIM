import processing.core.PApplet

class Activity {
  Integer start // inclusive
  Integer end   // exclusive
  int amount = 0
  PApplet pa
  int finished = 0
  int finishedAmount = 0
  def config = [distance: 2, barHeight: 1, showActivePeriod: true]

  def setQtos(List<Integer> qtos) {
    // assumes each qto assigns to exactly one boq item, which may not be true TODO: ensure no dups
    amount = qtos.sum()
  }

  def setTotal(int amount){
    this.amount = amount
  }

  public void setConfig(config){
    this.config += config
  }

  def reset() {
    finished = 0
    finishedAmount = 0
  }

  def reportFinished(List finishedQtos) {
    finishedAmount += finishedQtos?.sum() ?: 0
    finished = amount ? finishedAmount : Math.round(finishedAmount/amount*(end-start))
    // pa.map(finishedAmount, 0, amount, 0, end-start)
  }

  def reportFinished(Number finishedQto){
    finishedAmount += finishedQto
    finished = amount ? finishedAmount : Math.round(finishedAmount/amount*(end-start))
  }

  void reportMileStone(int reached) {
    if(reached > start){
      finished = Math.min(end, reached) - start
      // println "activity start=$start end=$end receiving milestone at $reached"
    }
  }

  def draw_expected(int index, int active) {
    def pos = index * config.barHeight * config.distance
    if (active >= start && active < end) {
      pa.fill(0, 0, 255) // should be active                                  blue
      pa.rect(active, pos, 1, config.barHeight)
    }
    if (active > start) {
      pa.fill(255, 0, 0) // should be finished                                    red
      pa.rect(start, pos, ([end, active].min()) - start, config.barHeight)
    }
    drawDummy(pos)
  }

  def draw_actual(int index) {
    def pos = index * config.barHeight * config.distance
    if (finished > 0){
      pa.fill(0,255,0)
      pa.rect(start, pos, finished, config.barHeight)
    }
    drawDummy(pos)
  }

  def draw(int index, int active) {
    def pos = index * config.barHeight * config.distance
    drawDummy(pos)
    if (active > start) {
      pa.fill(255, 0, 0) // should be finished                                    red
      pa.rect(start, pos, ([end, active].min()) - start, config.barHeight)
    }
    if (active > start && (finished > 0 || finished >= end - start)) {
      pa.fill(255, 255, 0) // is finished                                         yellow
      pa.rect(start, pos, finished, config.barHeight)
    }
    if (finished > 0 && finished > active - start + 1) {
      pa.fill(0, 255, 0) // is finished, but must not be                        green
      if (active > start) {
        pa.rect(active + 1, pos, finished - active + start - 1, config.barHeight)
      } else {
        pa.rect(start, pos, finished, config.barHeight)
      }
    }
    if (config.showActivePeriod && active >= start && active < end) {
      if (finished > active - start) {
        pa.fill(0, 255, 255) // should be active and is finished                cyan
      } else {
        pa.fill(0, 0, 255) // should be active                                  blue
      }
      pa.rect(active, pos, 1, config.barHeight)
    } else {
      // pa.fill(200, 200, 200)
      // pa.rect(active, pos, 1, config.barHeight)
    }
  }

  private def drawDummy(Number pos) {
    pa.fill(255)
    pa.rect(start, pos, (end - start), config.barHeight)
  }
}

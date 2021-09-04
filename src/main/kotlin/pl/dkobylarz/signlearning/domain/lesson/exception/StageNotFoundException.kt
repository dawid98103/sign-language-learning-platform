package pl.dkobylarz.signlearning.domain.lesson.exception

import java.lang.RuntimeException

class StageNotFoundException: RuntimeException("Nie znaleziono poziomu lekcji!")